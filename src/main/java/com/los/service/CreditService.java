package com.los.service;

import com.los.dto.request.CreditRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.CreditResponse;
import com.los.entity.Credit;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.CreditMapper;
import com.los.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;
    public CommonResponse createCredit(CreditRequest request){
        Credit credit = creditMapper.toCredit(request);
        credit.setIsDeleted(false);

        Credit savedCredit = creditRepository.save(credit);
        return  new CommonResponse(savedCredit.getId());
    }

    public List<CreditResponse> getAllCredit(){
        List<Credit> credits = creditRepository.findAll();
        return creditMapper.toCreditResponses(credits);
    }

    public CreditResponse getByIdCredit(Long id){
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        if (optionalCredit.isEmpty()){
            throw new ResourceNotFoundException("Credit not found with id : " + id);
        }

        Credit credit = optionalCredit.get();
        return creditMapper.toCreditResponse(credit);
    }

    public CommonResponse updateCredit(Long id, CreditRequest request){
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        if (optionalCredit.isEmpty()){
            throw new ResourceNotFoundException("Credit not found with id : " + id);
        }

        Credit credit = optionalCredit.get();
        creditMapper.updateCredit(request, credit);

        Credit updatedCredit = creditRepository.save(credit);
        return new CommonResponse(updatedCredit.getId());
    }

    public void deleteCredit(Long id){
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        if (optionalCredit.isEmpty()){
            throw new ResourceNotFoundException("Credit not found with id : " + id);
        }

        creditRepository.delete(optionalCredit.get());
    }
}
