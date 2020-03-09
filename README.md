# dash-wallet (V1 ready)
Spring Boot based 2-of-3 multisig wallet implementation for DASH. If you find any problem, please open an issue.

## Dashj-BLS lib
libdashjbls.so is in etc folder, copy it to folder in path or set java.library.path pointing to it.

## Setup Development Environment
1. Install PostgreSQL and Java 8.
2. Create a new role. user name is "wallet", password is "password". Set the privileges to yes for all.
3. Create a new DB, name is "dashWallet", set the owner to the newly created role.
4. Run ltc-wallet-1.0.0-SNAPSHOT.jar by using "java -jar dash-wallet-1.0.0-SNAPSHOT.jar", If you want to run it on DASH mainnet, please use prod profile.
5. Copy the checkpoint file in etc folder to <user_home> folder if you want to accelarate the blockchain download speed.
6. When you see "All blocks have been downloaded. DASH wallet service is available." in your log, you system has started. It takes less than one minute if you use checkpoint. hours if not.

Note: If you have problem about dash-core.jar in the pom, please add it to your maven repo manually.

## REST API 
- **List all transactions:  GET** https://hostname:9400/api/v1/dash/wallet/{walletId}/transaction/all?pageId=0&size=100

  example output:
  
  ```javascript
  {
  "size": 1,
  "transactions": [
    {
      "transactionId": "6d83d0d6f8e5fdf7e0849aaafa8badcf473d97e5e2b160878399146ced929ce5",
      "status": "confirmed",
      "fee_string": "0",
      "created_date": "2020-03-09T16:14:17.851Z",
      "wallet_id": "fa54552c75a44d5aad3e49c6b56f291c",
      "transaction_type": "DEPOSIT",
      "outputs": [
        {
          "amount_string": "0.1",
          "amount_in_smallest_unit_string": "10000000",
          "receive_address": "8nxYzyJxjXPU4SfFiPK49o3dv5VXfGiMy6",
          "index": 0
        }
      ]
    }
  ]
}
  ```


- **Create wallet:  POST** https://hostname:9400/api/v1/dash/wallet/new

    wallet-per-user is supported. You can create one or multiple wallets for one user. Based on our performance test, each microservice should be able to support 200 wallet.
  
  example input:
  ```javascript
  {
  	"symbol": "DASH",
  	"label":"test wallet 001",
  	"signing_key_passphrase":"abcdefg",
  	"backup_signing_key_passphrase":"abcdefg",
  	"enabled":true
  }
  ```
  
  example output:
  ```javascript
  {
    "id": "36c156efe2774effb9dfaf9dc966d89e",
    "enabled": true,
    "created_date": "2020-02-29T15:07:39.438267300Z",
    "encrypted_signing_key": "asbqxN1NPydktlq35IIoDkUZlGRZlZtpSPewIfEqPnKwVNcQfNphPExBvWgUW8WZ",
    "encrypted_backup_key": "RjSPVy6NQzu49umyuq3IQrS8N5eEfrjMh3ArEzbVHS8BaEPVrTttUo8p96Lo1JLB",
    "iv_spec": "ap6uVFj9QPfXH3NQhK1zFA==",
    "salt": "+6D9ZdJpgBwja6b37CCr6Q==",
    "seed_creation_time": 1582988859
  }
  ```

  
- **Get wallet:  GET**  https://hostname:9400/api/v1/dash/wallet/{walletId}

    output example:
    ```javascript
    {
      "id": "36c156efe2774effb9dfaf9dc966d89e",
      "enabled": true,
      "createdDate": "2020-02-29T15:07:39.438267Z",
      "label": "test wallet 001",
      "balance": {
        "estimated": "0.15157122",
        "available": "0.15157122"
      }
    }
    ```
    
- **List wallets:  GET**  https://hostname:9400/api/v1/dash/wallet/all?pageId=0&size=10

    output example:
    
    ```javascript
    {
      "wallets": [
        {
          "id": "7aaa9fcb5fad4764a0e394f9ad4c35c4",
          "enabled": true,
          "createdDate": "2020-03-01T16:25:07.567015Z",
          "label": "test wallet 001",
          "balance": {
            "estimated": "0",
            "available": "0"
          }
        },
        {
          "id": "5f81982cefca4e308db13f43f96aebea",
          "enabled": true,
          "createdDate": "2020-03-01T16:25:06.466924Z",
          "label": "test wallet 001",
          "balance": {
            "estimated": "0",
            "available": "0"
          }
        },
        {
          "id": "d78a8b2fe3824732bdaecdc16c32f561",
          "enabled": true,
          "createdDate": "2020-03-01T16:25:04.957556Z",
          "label": "test wallet 001",
          "balance": {
            "estimated": "0",
            "available": "0"
          }
        },
        {
          "id": "36c156efe2774effb9dfaf9dc966d89e",
          "enabled": true,
          "createdDate": "2020-02-29T15:07:39.438267Z",
          "label": "test wallet 001",
          "balance": {
            "estimated": "0.15157122",
            "available": "0.15157122"
          }
        },
        {
          "id": "71e7f9ccaa0d4dbabd762c3808f64da0",
          "enabled": true,
          "createdDate": "2020-02-29T04:51:19.171487Z",
          "label": "test wallet 001",
          "balance": {
            "estimated": "0",
            "available": "0"
          }
        }
      ]
    }
    ```

- **Generate receiving address:  POST**   https://hostname:9400/api/v1/dash/wallet/{walletId}/address/new

    example input:
    ```javascript
  {
  	"symbol":"DASH",
    "label": "testing"
  }
  ```
  
  example output:
  ```javascript
  {
    "address": "9gXBSfzMed9yeRc7pntu4hkoo1b2seQxFD",
    "label": "user1asdfasdfasf"
  }
  ```

- **Get Balance:  GET**   https://hostname:9400/api/v1/dash/wallet/{walletId}}/balance

    example output:
    ```javascript
    {
      "id": "36c156efe2774effb9dfaf9dc966d89e",
      "balance": {
        "estimated": "0.17545148",
        "available": "0.17545148"
      }
    }
    ```

- **Send coin directly:   POST**   https://hostname:9400/api/v1/dash/wallet/{walletId}/send

   example input:
    
   ```javascript
      {
       "symbol":"DASH",
       "internal_id": "alskjdflaksdlfk",
       "number_block":6,
       "signing_key_passphrase":"abcdefg",
       "using_backup_signer":false,
       "recipients":
       [
       	{
       		"address":"9QiHk1ma1VzqyeK7aDsh6YWvGiX35HAFViE",
       		"amount":"1133110"
       	},
       	{
       		"address":"8Qi3RnHucANAFtCrMBLT2xNVoAVqcSQkjEW",
       		"amount":"1122110"
       	}
       ]
       }
   ```
     
   example output:
   
   ```javascript
      {
        {
          "transaction_id": "ae9aa64c88a93a87eb676e84c41f4aa0af377a3c656cf40f19adaa175d9521eb"
        }
      }
   ```
- **Sign Transaction:   POST**   https://hostname:9400/api/v1/dash/wallet/{walletId}/sign

    example input:
    ```javascript
          {
           "symbol":"DASH",
           "internal_id": "alskjdflaksdlfk",
           "number_of_block":6,
           "signing_key_passphrase":"abcdefg",
           "using_backup_signer":false,
           "recipients":
           [
           	  {
           		"address":"9QiHk1ma1VzqyeK7aDsh6YWvGiX35HAFViE",
           		"amount":"1133110000000"
           	  },
           	  {
           		"address":"8Qi3RnHucANAFtCrMBLT2xNVoAVqcSQkjEW",
           		"amount":"1133110000000"
           	  }
           ]
           }
    ```
          
    example output:
          
    ```javascript
          {
            "fee_in_string": "0.00001804",
            "transaction_hex": "0100000001eb393c1f9ea9a516b5c3e77c33839c66e82e4d1271decba5d2baa7f30fd8b71001000000fc0047304402205548ddd4b60b4a5bcd8dc0f55c80fc51ed15e301fe40219acddf01e141c9b3fb022072383faf7395a9fde8514f8b0809a78369ac98724b5d4d59ab8adc727d7fb63701473044022078e3ca7a204e513b78b3041fa143cf313d490fa4f5dc3b5384242e8046f707b6022067cafc27b082bf7e5133d2a1134b45809717a287889c895f2db871862e89770f014c69522102198b151d36179bd87a740e198b0122d10088f497a0ffec418154510ed880c0e2210262491149733e1950da219c64ad1a67044ce418f209d90cdefd48cb10af0b0a752103e2490c98cdcf29c5967d177f50261f86d6c128118b7ce3b09e49dac8f53e730353aeffffffff03462674000000000017a9146e00f3c8bc3ef01618988ffb047621db2b43f028871f48120000000000160014c24d37ae375ff8910346cd308c7e2850d542bd810f2112000000000017a91454733e69914609b27e84af7e8ffe6aff46106ab78700000000",
            "number_blocks": 6
          }
    ```
          
- **Broadcast Transaction:  POST**   https://hostname:9400/api/v1/dash/wallet/{walletId}/broadcast

    example input:
           
    ```javascript
       {
            "transaction_hex":"0100000001eb393c1f9ea9a516b5c3e77c33839c66e82e4d1271decba5d2baa7f30fd8b71001000000fc0047304402205548ddd4b60b4a5bcd8dc0f55c80fc51ed15e301fe40219acddf01e141c9b3fb022072383faf7395a9fde8514f8b0809a78369ac98724b5d4d59ab8adc727d7fb63701473044022078e3ca7a204e513b78b3041fa143cf313d490fa4f5dc3b5384242e8046f707b6022067cafc27b082bf7e5133d2a1134b45809717a287889c895f2db871862e89770f014c69522102198b151d36179bd87a740e198b0122d10088f497a0ffec418154510ed880c0e2210262491149733e1950da219c64ad1a67044ce418f209d90cdefd48cb10af0b0a752103e2490c98cdcf29c5967d177f50261f86d6c128118b7ce3b09e49dac8f53e730353aeffffffff03462674000000000017a9146e00f3c8bc3ef01618988ffb047621db2b43f028871f48120000000000160014c24d37ae375ff8910346cd308c7e2850d542bd810f2112000000000017a91454733e69914609b27e84af7e8ffe6aff46106ab78700000000",
            "transaction_memo": "1234567890"
       }
    ```
            
    example output:
          
    ```javascript
        {
            "transaction_id": "ae9aa64c88a93a87eb676e84c41f4aa0af377a3c656cf40f19adaa175d9521eb"
        }
    ```


