package com.ashley.receipt_processor.Model;

import lombok.Data;

@Data
public class Receipt {
   private String retailer;
   private String purchaseDate;
   private String purchaseTime;
   private Item[] items;
   private String total;

   @Data
   public static class Item {
      private String shortDescription;
      private String price;
   }
}
