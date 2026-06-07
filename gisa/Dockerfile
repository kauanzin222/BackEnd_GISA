# ==========================================
# ETAPA 1: Compilação (Build)
# ==========================================
# Baixa uma imagem do Maven com Java 21 para compilar o projeto
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia todos os arquivos do seu repositório para dentro do contêiner
COPY . .

# Executa o comando do Maven para buildar o projeto e gerar o .jar (pulando os testes)
RUN mvn clean package -DskipTests

# ==========================================
# ETAPA 2: Execução (Runtime)
# ==========================================
# Baixa uma imagem super leve apenas com o ambiente de execução do Java (JRE)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia o arquivo .jar gerado na Etapa 1 para esta nova imagem limpa
COPY --from=build /app/target/*.jar app.jar

# Informa que a aplicação escuta na porta 8080 por padrão
EXPOSE 8080

# Comando final que liga o Spring Boot, injetando dinamicamente a porta que o Render fornecer
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]