# receipt-processor

## **Setup Instructions**

### **1. Clone the Repository**

Clone the repository to your local machine using the following commands:

```bash
git clone https://github.com/ychen621/receipt-processor.git
cd receipt-processor
```

### **2. Build and Run Using Docker**
a. Install all the maven dependecy
   ```bash
   mvn clean install
   ```
b. Run on Docker
  ```bash
  docker build -t receipt-processor .
  docker run -p 8080:8080 receipt-processor
  ```

### **3. Test the receipt processor**

Try the endpoints in Postman or Browser
> The application is running on "localhost:8080"

Endpoint 1. Process Receipt 
* Path: `/receipts/process`
* Method: `POST`
* Payload: Receipt JSON
* Response: JSON containing an id for the receipt.

Endpoint 2. Get Point
* Path: `/receipts/{id}/points`
* Method: `GET`
* Response: A JSON object containing the number of points awarded.
