# Requisitos Extra

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