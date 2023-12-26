package com.example.pmproject.Service;

import com.example.pmproject.DTO.ShopDTO;
import com.example.pmproject.Entity.Shop;
import com.example.pmproject.Repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {

    @Value("${shopImgUploadLocation}")
    private String shopImgUploadLocation;
    private final FileService fileService;
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper=new ModelMapper();

    public Page<ShopDTO> shopDTOS(String keyword, Pageable pageable) {
        int page=pageable.getPageNumber()-1;
        int pageLimit=5;

        Page<Shop> paging;
        if(!Objects.equals(keyword, "")) {
            paging=shopRepository.findByLocation(keyword, PageRequest.of(page, pageLimit, Sort.Direction.ASC, "shopId"));
        }else {
            paging=shopRepository.findAll(PageRequest.of(page, pageLimit, Sort.Direction.ASC, "shopId"));
        }

        return paging.map(shop -> ShopDTO.builder()
                .shopId(shop.getShopId())
                .name(shop.getName())
                .content(shop.getContent())
                .location(shop.getLocation())
                .tel(shop.getTel())
                .img(shop.getImg())
                .build());
    }

    public void register(ShopDTO shopDTO, MultipartFile imgFile) throws Exception {
        String originalFileName = imgFile.getOriginalFilename();
        String newFileName = "";

        if(originalFileName != null) {
            newFileName = fileService.upload(originalFileName, shopImgUploadLocation, imgFile.getBytes());
        }
        shopDTO.setImg(newFileName);
        Shop shop = modelMapper.map(shopDTO, Shop.class);
        shopRepository.save(shop);
    }

    public ShopDTO listOne(Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        return modelMapper.map(shop, ShopDTO.class);
    }

    public void modify(ShopDTO shopDTO, MultipartFile imgFile) throws Exception {
        Shop shop = shopRepository.findById(shopDTO.getShopId()).orElseThrow();
        String deleteFile = shop.getImg();

        if(imgFile != null) {
            String originalFileName = imgFile.getOriginalFilename();
            String newFileName = "";

            if(originalFileName.length() != 0) {
                if(deleteFile != null ) {
                    fileService.deleteFile(deleteFile, shopImgUploadLocation);
                }

                newFileName = fileService.upload(originalFileName, shopImgUploadLocation, imgFile.getBytes());
                shopDTO.setImg(newFileName);
            }
        }else {
            shopDTO.setImg(shop.getImg());
        }

        shopDTO.setShopId(shop.getShopId());
        Shop modify = modelMapper.map(shopDTO, Shop.class);

        shopRepository.save(modify);
    }

    public void delete(Long shopId) throws Exception {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        if(shop.getImg() != null) {
            fileService.deleteFile(shop.getImg(), shopImgUploadLocation);
        }

        shopRepository.deleteById(shopId);
    }
}
