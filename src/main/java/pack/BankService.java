package pack;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BankService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api/";
    public BankService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public List<Bank> getAllBanks(){
        ResponseEntity<Bank[]> responseEntity =
                restTemplate.getForEntity(BASE_URL+"/getAllBanks", Bank[].class);
        Bank[] userArray = responseEntity.getBody();
        return Arrays.asList(userArray);
    }

    public Bank consultBanque(int nBanque){
        try {
            return restTemplate.getForObject(BASE_URL+nBanque,Bank.class);
        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)return null;
            else throw e;
        }
    }

    public void deleteBank(int nBanque){
        try {
            restTemplate.delete(BASE_URL + nBanque);
        }catch(HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND);
        }
    }
    public void addBanque(Bank bank){
        try {
            HttpEntity<Bank> request = new HttpEntity<>(bank);
            System.out.println(restTemplate.postForObject(BASE_URL + "addbank", request, Bank.class));
        }catch(Exception e){e.printStackTrace();}
    }
    public void modifierBanque(Bank bank){
        try {
            HttpEntity<Bank> request = new HttpEntity<>(bank);
            System.out.println(restTemplate.patchForObject(BASE_URL+"editbank" , request, Bank.class));
        }catch(Exception e){e.printStackTrace();}
    }
}
