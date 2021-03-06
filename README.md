# gatling-demo


## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Gatling.io stress test performance automation demo template project.

To show performance test, using similar as Java functional page object structure, with modern Grafana Dashboards

### Built With

Frameworks and tools used to built this project.
* [Gatling.io](https://gatling.io/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [AdoptOpenJDK 1.8](https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u292-b10/)
* [Maven](https://maven.apache.org/)
* [SCALA 2.12.1](https://www.scala-lang.org/download/2.12.1.html) (IntelliJ IDEA plugin)
* [Docker](https://www.docker.com/)


### Prerequisites

 1. Install (new or add another) JAVA JDK 8
 2. Get IntelliJ IDEA, which give you Maven
 3. Install Docker to your project run unit only if you need to use Grafana dashboard 

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/ryuest/gatling-demo.git
   ```
2. Open IntelliJ for project and set JAVA 8
   
3. Download Scala 2.12.1 to IntelliJ plugins


## Usage

* Add IntelliJ new configuration maven run
```sh
   clean install -DsimulationClass=LottoLogin
   ```

## Docker Images

The 2 images for this setup are Grafana, front end, with InfluxDB for the backend.  

* Setup

Use docker-compose to build the images locally, edit the configuration files to your specific needs.

```sh
docker-compose build
```

* Run

```
docker-compose up -d
```

You should see 2 container ID's and everything should be running. 
 
 Grafana Gatling [localhost:3000](http://localhost:3000)

 InfluxDB [localhost:8086/query?q=SHOW+SERIES+ON+graphite](http://localhost:8086/query?q=SHOW+SERIES+ON+graphite)

<!-- ACKNOWLEDGEMENTS -->
## Docs to read

https://gatling.io/docs/gatling/tutorials/advanced/

https://gatling.io/docs/gatling/guides/realtime_monitoring/

https://gatling.io/docs/gatling/reference/current/general/reports/


[product-screenshot]: images/Screenshot_50.png

