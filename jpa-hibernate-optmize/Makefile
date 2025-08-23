# Library Management System Makefile


SOURCES := $(shell find src -name "*.java")

compile-project:
	@echo "Compiling Java project..."
	mvnw clean compile exec:java -Dexec.mainClass="com.library_management_system.app.LmsApplication"
	mkdir -p bin/META-INF && cp -r src/META-INF/* bin/META-INF/
	@echo "✅ Compilation completed successfully!"


run-project:
	@echo "Running Library Management System..."
	java -cp bin:$(EXTERNAL_JARS) Main

clean:
	@echo "Cleaning compiled files..."
	rm -rf bin/*
	@echo "✅ Clean completed!"

# Check if Docker is running
check-docker:
	@docker info > /dev/null 2>&1 || (echo "❌ Docker is not running!" && exit 1)

start-db: check-docker
	@echo "🐘 Starting PostgreSQL Docker container..."
	docker-compose up -d postgresql
	@echo " ✅ PostgreSQL container started! "

stop-db:
	@echo "🛑 Stopping PostgreSQL Docker container..."
	docker-compose down
	@echo " ✅ PostgreSQL container stopped! "


run: compile-project run-project


# alternative run command
# run:
# 	$(MAKE) compile-project 
# 	$(MAKE) start-db 
# 	$(MAKE) run-project 
# 	$(MAKE) clean 
# 	$(MAKE) stop-db 


.PHONY: compile-project run-project clean check-docker start-db stop-db run run-no-db
