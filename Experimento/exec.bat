set /p rprov_path = "C:\Users\Gaburieru\Documents\Git\RProvenance\Experimento\RProv.jar"
set /p yw_path = "C:\Users\Gaburieru\Documents\Git\RProvenance\Experimento\yw.jar"
set /p prov_dir = "C:\Users\Gaburieru\Documents\Git\RProvenance\Experimento"
set /p script_path = "C:\Users\Gaburieru\Documents\Git\RProvenance\Experimento\Experimento.R"
set /p json_path = "C:\Users\Gaburieru\Documents\Git\RProvenance\Experimento\prov_Experimento\prov.json"
set /p script_yw_path = "C:\Users\Gaburieru\Documents\Git\RProvenance\Experimento\prov_Experimento\prov.YW.R"
set /p ctrl = ""
set /p R_path = ""







R
library(rdt)
prov.run(%SCRIPT_PATH%,prov.dir=%PROV_DIR%)
quit()
pause

java -jar %RPROV_PATH% %JSON_PATH% %SCRIPT_PATH% %CTRL%
pause 

set /p gv_path = %SCRIPT_PATH%.gv
java -jar %YW_PATH% graph %SCRIPT_PATH% > %GV_PATH%
pause

set /p pdf_path = %SCRIPT_PATH%.pdf
dot -Tpdf %GV_PATH% > %PDF_PATH%
pause 

%PDF_PATH%