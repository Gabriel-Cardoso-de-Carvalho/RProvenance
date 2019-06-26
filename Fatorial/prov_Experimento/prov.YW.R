#@BEGIN Experimento
#@IN config.txt
#@IN entrada.csv
#@OUT fatorial.csv
#@OUT fibonacci.csv

fatorial <- function(v){
  resultado <- v
  
  for (val in 1:nrow(v)){
    aux <- 1
    
    if(v[val,1] >1){
      for (j in 1:v[val,1]) {
        aux <- aux * j
      }
    }
    
    resultado[val,1] <- aux
  } 
  return(resultado)
}


fibonacci <- function(v){
  resultado <- v
  
  for (val in 1:nrow(v)){
    aux1 <- 0
    aux2 <- 1
    
    if(v[val,1] == 1){
      resultado[val,1] <- aux1
    }else{
      if(v[val,1] >2){
        for (j in 3:v[val,1]) {
            temp <- aux1
            aux1 <- aux2
            aux2 <- aux1+temp
          }
      }
      
      resultado[val,1] <- aux2
    }
  } 
  return(resultado)
}


#@BEGIN read.csv
#@IN config.txt
#@OUT diretorio
diretorio <- read.csv("C:/Users/Gaburieru/Documents/Git/RProvenance/Fatorial/config.txt")
#@END read.csv


#@BEGIN read.csv
#@IN diretorio
#@IN entrada.csv
#@OUT dados
dados <- read.csv(paste(diretorio[1,2],"entrada.csv",sep = ""))
#@END read.csv


#@BEGIN fatorial
#@IN dados
#@OUT x
x <- fatorial(dados)
#@END fatorial

#@BEGIN fibonacci
#@IN dados
#@OUT y
y <- fibonacci(dados)
#@END fibonacci


#@BEGIN write.csv
#@IN x
#@IN diretorio
#@OUT fatorial.csv
write.csv(x,paste(diretorio[2,2],"fatorial.csv",sep = ""))
#@END write.csv

#@BEGIN write.csv
#@IN y
#@IN diretorio
#@OUT fibonacci.csv
write.csv(y,paste(diretorio[2,2],"fibonacci.csv",sep = ""))
#@END write.csv
#@END Experimento
