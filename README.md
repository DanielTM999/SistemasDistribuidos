# inicialização 

inicio as aplicações ou na pasta build usando java -jar aplicação.jar ou pela ide 

# ISP Server
instruções de como utilizar os endpoints expostos pela aplicação ISP Server para salvar e obter arquivos.

## Pré-requisitos

- Um cliente HTTP (ex: Postman, Insomnia)

## Endpoints

### 1. Salvar Arquivo
Este endpoint permite salvar um arquivo no servidor.

**URL**: `/salvarArquivo/{archiverName}`  

**Método**: `POST`  
**Parâmetros de URL**: 
- `archiverName` - Nome do arquivo a ser salvo (extensão opcional). A extensão será sempre `.txt`.

**Parâmetros de Formulário**:
- `file` - Arquivo a ser salvo (apenas arquivos `.txt` são permitidos).

## Usando os Endpoints com Postman e Insomnia

#### Usando Postman

1. Selecionar o método `POST`.
2. Inserir a URL: `http://localhost:8080/salvarArquivo/teste.txt`.
3. Na aba "Body", selecionar "form-data".
4. Adicionar um campo com chave `file` e selecionar o arquivo `.txt` para upload.
5. Enviar a requisição.

#### Usando Insomnia

1. Criar uma nova requisição e nomeá-la, por exemplo, `Salvar Arquivo`.
2. Selecionar o método `POST`.
3. Inserir a URL: `http://localhost:8080/salvarArquivo/teste.txt`.
4. Na aba "Body", selecionar "Multipart Form".
5. Adicionar um campo com chave `file`, selecionar "File" e escolher o arquivo `.txt` para upload.
6. Enviar a requisição.

**Resposta**:

- **200 OK** - Se o arquivo for salvo com sucesso.
- **400 Bad Request** - Se houver algum erro com o arquivo enviado (ex: arquivo vazio, extensão inválida).

### Obter Arquivo

Este endpoint permite obter um arquivo salvo no servidor.

**URL**: `/obterArquivo/{archiverName}`  
**Método**: `GET`  
**Parâmetros de URL**:

- `archiverName` - Nome do arquivo a ser obtido (extensão opcional).

#### Exemplo de Requisição:

##### Usando Postman

1. Selecionar o método `GET`.
2. Inserir a URL: `http://localhost:8080/obterArquivo/teste_v1.txt`.
3. Enviar a requisição.
4. O arquivo será baixado e salvo localmente.

##### Usando Insomnia

1. Criar uma nova requisição e nomeá-la, por exemplo, `Obter Arquivo`.
2. Selecionar o método `GET`.
3. Inserir a URL: `http://localhost:8080/obterArquivo/teste_v1.txt`.
4. Enviar a requisição.
5. O arquivo será baixado e salvo localmente.

**Resposta**:

- **200 OK** - O arquivo será retornado como um arquivo de download.
- **400 Bad Request** - Se o `archiverName` for nulo ou vazio.
- **500 Internal Server Error** - Se houver um erro ao processar o arquivo no servidor.


## Exceções e Tratamento de Erros

*BadRequestException*: Esta exceção é lançada quando há um problema com a requisição do cliente, como um arquivo inválido ou nome de arquivo nulo.
*BadGatewayException*: Esta exceção é lançada quando há um problema ao se comunicar com um serviço externo.