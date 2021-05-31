package com.munnicha.voucher;

import com.munnicha.voucher.type.CreateVoucherResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.munnicha.voucher.exception.BadRequestException;
import com.munnicha.voucher.service.VoucherManagerService;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author munnicha
 * 
 * API Documentation: http://{host}/{context-path}/swagger-ui.html
 * Open API: http://{host}/{context-path}/v3/api-docs
 */
@RestController
public class VoucherManagementController {
    
    private static final Logger LOG = Logger.getLogger(VoucherManagementController.class.getName());
    
    @Autowired
    VoucherManagerService voucherService;
    
    @PostMapping(path="/admin/createVouchers", consumes="application/json", produces="application/json")
    public ResponseEntity<CreateVoucherResponse> createVouchers(@RequestBody CreateVoucherRequest request)  throws BadRequestException{
        LOG.log(Level.INFO, "/admin/createVouchers.request:\n{0}", convertObjectToJson(request));
        CreateVoucherResponse response= new CreateVoucherResponse();
        if(request==null ||
           request.getVouchers()==null ||
           request.getVouchers().isEmpty()){
            throw new BadRequestException(); 
        }
        if(request.getRequestId()==null ||
           "".equalsIgnoreCase(request.getRequestId())){
            request.setRequestId(UUID.randomUUID().toString());
        }
        response.setRequestId(request.getRequestId());
        List<CreateVoucherResult> voucherList = voucherService.createVouchers(request.getVouchers());
        response.setVoucherResponses(voucherList);
        HttpHeaders header = new HttpHeaders();
        header.add("Responded", "Voucher Management service - createVouchers");
        LOG.log(Level.INFO, "Response:\n{0}", convertObjectToJson(response));
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(response);
    }
    
    
    @PostMapping(path="/reedem", produces="application/json", params={"voucherId"})
    public ResponseEntity<RedeemVoucherResponse> redeemVoucherById(@RequestParam Long voucherId) throws BadRequestException{
        LOG.log(Level.INFO, "Request:\n/redeem?voucherId={0}", voucherId);
        RedeemVoucherResponse response=voucherService.redeemVoucher(voucherId);
        HttpHeaders header = new HttpHeaders();
        header.add("Responded", "Voucher Management service - redeemVoucherById");
        LOG.log(Level.INFO, "Response:\n{0}", convertObjectToJson(response));
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(response);
    }
    
    private String convertObjectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String serializedObject;
        try {
            if(obj!=null){
                serializedObject = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            }else{
                return "Object is null";
            }
        } catch (JsonProcessingException ex) {
            return null;
        }
        return serializedObject;
    }
    
}
