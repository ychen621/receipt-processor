package com.ashley.receipt_processor.Service;

import com.ashley.receipt_processor.Model.Receipt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
   private final Map<String, Integer> receiptPointMap = new HashMap<>();

   public String processReceipt(Receipt receipt) {
      String id = UUID.randomUUID().toString();
      int point = calculatePoint(receipt);
      receiptPointMap.put(id, point);
      return id;
   }

   public int getPoint(String id) {
      return receiptPointMap.getOrDefault(id, -1);
   }

   private int calculatePoint(Receipt receipt) {
      int res = 0;

      // 1. One point for every alphanumeric character in the retailer name.
      res += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

      // 2. 50 points if the total is a round dollar amount with no cents.
      if(receipt.getTotal().matches("\\d+\\.00")) { res += 50; }

      // 3. 25 points if the total is a multiple of 0.25.
      if(Double.parseDouble(receipt.getTotal()) % 0.25 == 0) { res += 25; }

      // 4. 5 points for every two items on the receipt.
      res += (receipt.getItems().length / 2)*5;

      // 5. If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
      for(Receipt.Item item : receipt.getItems()) {
         String description = item.getShortDescription().trim();
         if(description.length() % 3 == 0){
            res += (int) Math.ceil(Double.parseDouble(item.getPrice())*0.2);
         }
      }

      // 6. 6 points if the day in the purchase date is odd.
      String[] date = receipt.getPurchaseDate().split("-");
      if(Integer.parseInt(date[2]) % 2 != 0) { res += 6;}

      // 8. 10 points if the time of purchase is after 2:00pm and before 4:00pm.
      String[] time = receipt.getPurchaseTime().split(":");
      int hour = Integer.parseInt(time[0]);
      if(hour >= 14 && hour <= 16) { res += 10; }

      return res;
   }
}
