To run project services

1. Build and create the images

   1.1 Set console path in root directory project

   1.2 execute commands

   > docker build -t usuarios -f .\ms-users\Dockerfile .

   > docker build -t cursos -f .\ms-courses\Dockerfile .
2. Create container network
   
   > docker network create spring

3. Run a container for each image created previously

   > docker run -d --name msvc-usuarios --network spring --env-file ./Deployment/common.env --env-file ./Deployment/users.env -p 8082:8082 --rm usuarios
    
   > docker run -d --name msvc-courses --network spring --env-file ./Deployment/common.env --env-file ./Deployment/courses.env -p 8083:8083 --rm cursos