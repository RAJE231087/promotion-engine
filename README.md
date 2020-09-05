# promotion-engine

Please find the end point to calculate price.

http://localhost:8080/product/promotion/Order
Method: Post
Header:
accept :application/json
content-type :application/json
Body:

[
  {
    "productType": "A",
    "orderCount": 1
  },
  {
    "productType": "A",
    "orderCount": 2
  },
  {
    "productType": "B",
    "orderCount": 5
  },
  {
    "productType": "C",
    "orderCount": 1
  },
  {
    "productType": "D",
    "orderCount": 1
  }
]

Response:
{
"productPrice": 280
}
