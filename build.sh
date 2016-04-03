#!/bin/bash
javac `find src -iname "*.java"`

if [[ $1 == "run" ]]
then
    (cd src && java me.mgerdes.raytracer.RayTracer)
fi

if [[ $1 == "clean" ]]
then
    rm `find src -iname "*.class"`
fi
