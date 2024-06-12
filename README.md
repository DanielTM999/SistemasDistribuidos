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

### Salvar Arquivo

#### Usando Postman

1. Selecionar o método `POST`.
2. Inserir a URL: `http://localhost:8080/salvarArquivo/teste`.
3. Na aba "Body", selecionar "form-data".
4. Adicionar um campo com chave `file` e selecionar o arquivo `.txt` para upload.
5. Enviar a requisição.

#### Usando Insomnia

1. Criar uma nova requisição e nomeá-la, por exemplo, `Salvar Arquivo`.
2. Selecionar o método `POST`.
3. Inserir a URL: `http://localhost:8080/salvarArquivo/teste`.
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

- `archiverName` - Nome do arquivo a ser obtido (sem a extensão).

## Exceções e Tratamento de Erros

*BadRequestException*: Esta exceção é lançada quando há um problema com a requisição do cliente, como um arquivo inválido ou nome de arquivo nulo.
*BadGatewayException*: Esta exceção é lançada quando há um problema ao se comunicar com um serviço externo.