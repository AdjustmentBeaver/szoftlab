#!/bin/bash

find . -type f -not -path "*out/*" -not -path "*.idea/*" -not -path "*.iml*" -printf '| %P | %s | %TY-%Tm-%Td %TH:%TM |  |\n'
