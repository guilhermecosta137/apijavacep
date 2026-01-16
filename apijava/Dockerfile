# Usa imagem base atualizada e mantida pela Eclipse Foundation
FROM eclipse-temurin:17-jre-alpine

# Define variáveis de ambiente para otimização da JVM
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseContainerSupport"

# Cria um usuário não-root para segurança
RUN addgroup -S spring && adduser -S spring -G spring

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR da aplicação
COPY --chown=spring:spring target/cep-consultation-1.0.0.jar app.jar

# Muda para o usuário não-root
USER spring:spring

# Expõe a porta da aplicação
EXPOSE 8080

# Configura healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Executa a aplicação com as otimizações da JVM
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
