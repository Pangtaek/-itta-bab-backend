package com.fivemybab.ittabab.store.command.service;

import com.fivemybab.ittabab.store.command.dto.StoreDto;
import com.fivemybab.ittabab.store.command.domain.aggregate.Store;
import com.fivemybab.ittabab.store.command.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    public StoreService(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    /* 가게 조회 */
    /* 조회 부분은 Mybatis 사용 */
    public List<StoreDto> findStoreList() {
        List<Store> storeList = storeRepository.findAll(Sort.by("storeId").descending());
        return storeList.stream()
                .map(storeInfo -> modelMapper.map(storeInfo, StoreDto.class))
                .toList();
    }

    /* 가게 번호로 가게 조회 */
    public StoreDto findStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(store, StoreDto.class);
    }

    /* 가게 추가 */
    @Transactional
    public void registSchedule(StoreDto storeDto) {
        storeRepository.save(modelMapper.map(storeDto, Store.class));
    }

    /* 가게 정보 수정 */
    @Transactional
    public void modifyStore(StoreDto storeDto) {


    }

    /* 가게 삭제 */
    @Transactional
    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }

}
