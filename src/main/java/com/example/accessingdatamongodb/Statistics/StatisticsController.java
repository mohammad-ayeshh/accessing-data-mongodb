package com.example.accessingdatamongodb.Statistics;

import com.example.accessingdatamongodb.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@RestController
@RequestMapping("/Statistics")
public class StatisticsController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StatisticsRepository statisticsRepository;


    @Autowired
    private RestTemplate restTemplate;

    private Boolean verify() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authService.getAuthToken()); // Set the bearer token in the header

            String verifyServiceUrl = "http://auth-app:8081/verify";
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> response = restTemplate.exchange(verifyServiceUrl, HttpMethod.GET, entity, Boolean.class);


            boolean b = response.getBody() != null && response.getBody();
            System.out.println("verified");
            return b;
        } catch (HttpClientErrorException.Unauthorized e) {
            System.out.println("Unauthorized request");
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred during the GET request to /verify");
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping()
    public ResponseEntity<?> getStatistics() {
        if (!verify()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Statistics> optionalAnalysis = statisticsRepository.findTopByOrderByIdAsc();
        System.out.println(optionalAnalysis);
        if (optionalAnalysis.isPresent()) {
            return ResponseEntity.ok(optionalAnalysis);
        }

            return ResponseEntity.ok("The database is empty.");

    }
}