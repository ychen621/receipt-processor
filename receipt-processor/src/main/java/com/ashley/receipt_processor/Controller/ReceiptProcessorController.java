package com.ashley.receipt_processor.Controller;

import com.ashley.receipt_processor.DTO.ReceiptResponse;
import com.ashley.receipt_processor.Model.Receipt;
import com.ashley.receipt_processor.Service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {
   private final ReceiptService receiptService;

   @Autowired
   public ReceiptProcessorController(ReceiptService receiptService) {
      this.receiptService = receiptService;
   }

   @PostMapping("/process")
   public ResponseEntity<ReceiptResponse> process(@RequestBody Receipt receipt) {
      String id = receiptService.processReceipt(receipt);
      return ResponseEntity.ok(new ReceiptResponse(id));
   }

   @GetMapping("/{id}/points")
   public ResponseEntity<Object> getPoints(@PathVariable String id) {
      int points = receiptService.getPoint(id);
      return ResponseEntity.ok().body(Map.of("points", points));
   }
}
