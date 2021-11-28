


Echo INICIANDO O BUILD

call mvn clean package install -DTestSkip

call docker compose down
call docker compose up --build