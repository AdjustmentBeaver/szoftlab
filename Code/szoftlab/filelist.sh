#!/bin/bash

find . -printf '| %P | %s | %CY-%Cm-%Cd %CH:%Cm |  |\n'
