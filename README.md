# eCommerce Microservices Kubernetes

#### Pre-requirement
 `minikube and docker should be installed in the testing system.`

#### Start the minikube server with the following codes in the terminal
`minikube start`

#### Run the configuration of the kubernetes with the following codes from the root folder of the project.
`kubectl create -f k8s/config-secrets`  
`kubectl create -f k8s/appcore`

#### Run The microservices from the follwoing codes
`kubectl create -f k8s/services`

#### For tunneling the APIs run the following code
`minikube services --all`

### Using POSTMAN make requests as shown below  
#### Make requests to the authentication server for registration  with the following payloads
`method: POST`  

`api: /users/register`

```
{
    username: user,  
    password: password
}
```

#### With the same above payload make request to the same authentication server to get the access token.
`method: POST` 

`api: /users`

```
{
    username: user,
    password: password
}
```

#### Add a product in the catalog service with the following service
`method: POST`  

`api: /products`  
 
```
{
    "name": "iPad",
    "vendor": "Apple",
    "category": "Electronics",
    "price": 10.1,
    "inStock": 15
}
```

#### See the available products in the catalog service with the following API request
`method: GET`

`api: /products`

#### Create the following credit card entity on the credit card service with the following API request
`method: POST`

`/creditcards`
```
{
    "firstName": "John",
    "lastName": "Doe",
    "cardNumber": "98739219873123",
    "ccv": "3123",
    "cardLimit": 1000000.0,
    "expiryDate": "2029-01-01T00:00:00",
    "balance": 30000.0
}
```


#### Make an order in the order service with the following API request
`method: POST`

`api: /orders`

```
{
    "paymentType": "CC",
    "creditCardDTO": {
    "firstName": "John",
    "lastName": "Doe",
    "cardNumber": "98739219873123",
    "ccv": "3123",
    "expiryDate": "2029-01-01T00:00:00"
},
"orderItemList": [
    {
    "productId": 1,
    "quantity": 10
    }
],
"shippingDTO": {
    "city": "Fairfield",
    "state": "Iowa",
    "stAddress": "1000 Nth 4th St",
    "zip": 52557
    }
}
```




