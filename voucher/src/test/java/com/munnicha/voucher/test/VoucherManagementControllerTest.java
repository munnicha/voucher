package com.munnicha.voucher.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.munnicha.voucher.CreateVoucherRequest;
import com.munnicha.voucher.RedeemVoucherResponse;
import com.munnicha.voucher.VoucherManagementController;
import com.munnicha.voucher.model.NumberRedemption;
import com.munnicha.voucher.model.Voucher;
import com.munnicha.voucher.service.VoucherManagerService;
import com.munnicha.voucher.type.CreateVoucherResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;



/**
 *
 * @author munnicha
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(VoucherManagementController.class)
public class VoucherManagementControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private VoucherManagerService voucherService;
    
    
    @Test
    public void testCreateVouchers() throws Exception{
        //request data
        List<Voucher> vouchers = new ArrayList<>();
        Voucher voucher=new Voucher();
        voucher.setId(null);
        voucher.setName("Test Voucher");
        voucher.setDescription("Test description");
        NumberRedemption redemption=new NumberRedemption();
        redemption.setId(null);
        redemption.setName("Single Redemption");
        redemption.setMaxNumberOfRedemptions(1);
        voucher.setRedemption(redemption);
        vouchers.add(voucher);
        CreateVoucherRequest vReq=new CreateVoucherRequest("Test-Request-01",vouchers);
        //voucherResults
        List<CreateVoucherResult> voucherResults = new ArrayList<>();
        voucher.setId(1L);
        voucher.getRedemption().setId(1L);
        CreateVoucherResult res1=new CreateVoucherResult();
        res1.setCreated(true);
        res1.setMessage("Successful test voucher creation");
        res1.setVoucher(voucher);
        voucherResults.add(res1);

        
        //Mockito actions
        when(voucherService.createVouchers(Mockito.<Voucher>anyList())).thenReturn(voucherResults);

        ResultActions resActions = mvc.perform(MockMvcRequestBuilders.post("/admin/createVouchers")
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(vReq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(httpBasic("admin","admin")) //basic auth for demo
                );
        //expected result
        resActions.andExpect(status().isOk());
        
    }
    
    @Test
    public void testCreateVouchersError() throws Exception{
        //request data
        CreateVoucherRequest vReq=null;

        ResultActions resActions = mvc.perform(MockMvcRequestBuilders.post("/admin/createVouchers")
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(vReq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(httpBasic("admin","admin")) //basic auth for demo
                );
        //expected result
        resActions.andExpect(status().isBadRequest());
        
    }
    
    @Test
    public void testRedeemVoucherById() throws Exception{
        //response data
        RedeemVoucherResponse redeemResp=new RedeemVoucherResponse();
        redeemResp.setVoucherId(1L);
        redeemResp.setMessage("Test success message");
        redeemResp.setRedeemed(true);
        
        //Mockito actions
        when(voucherService.redeemVoucher(1L)).thenReturn(redeemResp);
        
        ResultActions resActions = mvc.perform(MockMvcRequestBuilders.post("/reedem")
                .param("voucherId", "1")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //expected result
        resActions.andExpect(status().isOk());
        
    }
    
    @Test
    public void testRedeemVoucherByIdError() throws Exception{
        //response data
        RedeemVoucherResponse redeemResp=new RedeemVoucherResponse();
        redeemResp.setVoucherId(1L);
        redeemResp.setMessage("Test error message");
        redeemResp.setRedeemed(true);
        
        //Mockito actions
        when(voucherService.redeemVoucher(1L)).thenReturn(redeemResp);
        
        ResultActions resActions = mvc.perform(MockMvcRequestBuilders.post("/reedem")
                .param("voucherId", "asd")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //expected result
        resActions.andExpect(status().isBadRequest());
        
    }
    
}
