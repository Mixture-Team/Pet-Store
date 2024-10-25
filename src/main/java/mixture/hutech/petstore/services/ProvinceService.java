package mixture.hutech.petstore.services;

import lombok.RequiredArgsConstructor;
import mixture.hutech.petstore.models.Province;
import mixture.hutech.petstore.repositories.ProvinceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProvinceService {
   private final ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }
}
