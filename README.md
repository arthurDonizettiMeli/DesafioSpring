# Requisitos Extras

## US 0013: Ranking de vendedores
Descrição: Obter a lista de vendedores mais populares conforme quantidade de seguidores.

Sign: 

| Method | Sign |
|---|---|
| GET | /users/popular-sellers/ranking?size={rankSize} |

### Filtros/Parâmetros:

| Parâmetros | Tipo | Descrição/Evento |
|:---|:---:|:---|
| rankSize | int | Tamanho do ranking desejado. Default: 10 |

### Response:
<pre>
[
    {
        "userId": 2,
        "userName": "vendedor1",
        "followers_count": 2
    },
    {
        "userId": 4,
        "userName": "vendedor2",
        "followers_count": 1
    }
]
</pre>


## US 0014: Atualizar um post
Descrição: Atualizar um Post, podendo editar desconto e promoções.

Sign: 

| Method | Sign |
|---|---|
| PATCH | /products/editpost/{postId} |

### Filtros/Parâmetros:

| Parâmetros | Tipo | Descrição/Evento |
|:---|:---:|:---|
| postId | int | Id do post a ser editado |

### Response:
<pre>
{
  "userId": 1569,
  "id_post" : 18,
  "date" : ”29-06-2021”,
  "detail" : { 
    “product_id” : 1,
    "productName" : "Cadeira Gamer",
    "type" : "Gamer",,
    "brand" : "Racer"
    "color" : "Red & Black",
    "notes" : "Special Edition"
             },
  "category" : "100",
  "price" : 1500.50,
  "hasPromo": true,
  "discount": 0.25
}
</pre>
