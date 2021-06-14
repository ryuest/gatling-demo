#!/usr/bin/env sh

if [ ! -f "/var/lib/influxdb/.init" ]; then
    exec influxd -config /etc/influxdb/influxdb.conf &


    until wget -q "http://localhost:8086/ping" 2> /dev/null; do
        sleep 1
    done

    influx -host=localhost -port=8086 -execute="CREATE USER admin WITH PASSWORD 'admin' WITH ALL PRIVILEGES"
    influx -host=localhost -port=8086 -execute="CREATE DATABASE graphite"

    touch "/var/lib/influxdb/.init"

    kill -s TERM %1
fi

exec influxd $@