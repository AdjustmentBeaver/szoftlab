#!/bin/bash

find . -printf '| %P | %s | %TY-%Tm-%Td %TH:%TM |  |\n'
