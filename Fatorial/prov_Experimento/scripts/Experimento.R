
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

diretorio <- read.csv("C:/Users/Gaburieru/Documents/Git/RProvenance/Fatorial/config.txt")

dados <- read.csv(paste(diretorio[1,2],"entrada.csv",sep = ""))

x <- fatorial(dados)
y <- fibonacci(dados)

write.csv(x,paste(diretorio[2,2],"fatorial.csv",sep = ""))
write.csv(y,paste(diretorio[2,2],"fibonacci.csv",sep = ""))

