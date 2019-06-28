#@BEGIN Experimento
#@IN exemplo04.txt
#@OUT dev.2
#@OUT ex04.avi
#@OUT ex04.mt
#@OUT ex04.avr
#@OUT ex04.ave

#@BEGIN read.table
#@IN exemplo04.txt
#@OUT ex04
ex04 <- read.table("C:/Users/Gaburieru/Documents/Git/RProvenance/Experimento/dados/entrada.txt", header=T)
#@END read.table

attach(ex04)

ex04.m <- tapply(resp, list(rec,esp), mean)

ex04.mr <- tapply(resp, rec, mean)

ex04.me <- tapply(resp, esp, mean)


#@BEGIN par
#@OUT ctrl1
par(mfrow=c(1,2))
#@END par

#@BEGIN interaction.plot
#@IN ctrl1
#@OUT ctrl2
interaction.plot(rec, esp, resp)
#@END interaction.plot

#@BEGIN interaction.plot
#@OUT ctrl3
interaction.plot(esp, rec, resp)
#@END interaction.plot


#@BEGIN aov
#@OUT ex04.avi
ex04.avi <- aov(resp ~ rec + esp + rec * esp)
#@END aov


#@BEGIN aov
#@OUT ex04.av
#@OUT ctrl4
ex04.av <- aov(resp ~ rec * esp)
#@END aov


#@BEGIN model.tables
#@IN ex04.av
#@OUT ex04.mt
ex04.mt <- model.tables(ex04.av, ty="means")
#@END model.tables


#@BEGIN par
#@IN ctrl2
#@OUT ctrl5
par(mfrow=c(2,2))
#@END par

#@BEGIN plot
#@IN ex04.av
#@IN ctrl3
#@OUT ctrl6
plot(ex04.av)
#@END plot


#@BEGIN par
#@IN ctrl4
#@OUT ctrl7
par(mfrow=c(2,1))
#@END par

#@BEGIN resid
#@IN ex04.av
#@OUT residuos
residuos <- resid(ex04.av)
#@END resid


#@BEGIN plot
#@IN ex04
#@IN residuos
#@OUT ctrl8
plot(ex04$rec, residuos)
#@END plot

#@BEGIN title
#@IN ctrl5
#@OUT ctrl9
title("Res�duos vs Recipientes")
#@END title


#@BEGIN plot
#@IN ex04
#@IN residuos
#@OUT ctrl10
plot(ex04$esp, residuos)
#@END plot

#@BEGIN title
#@IN ctrl6
#@OUT ctrl11
title("Res�duos vs Esp�cies")
#@END title


#@BEGIN par
#@IN ctrl7
#@OUT ctrl12
par(mfrow=c(2,2))
#@END par
preditos <- (ex04.av$fitted.values)

#@BEGIN plot
#@IN residuos
#@IN preditos
#@IN ctrl8
#@OUT ctrl13
plot(residuos, preditos)
#@END plot

#@BEGIN title
#@IN ctrl9
#@OUT ctrl14
title("Res�duos vs Preditos")
#@END title

#@BEGIN resid
#@IN ex04.av
#@OUT s2
s2 <- sum(resid(ex04.av)^2)/ex04.av$df.res
#@END resid
respad <- residuos/sqrt(s2)

#@BEGIN boxplot
#@IN respad
#@IN ctrl10
#@OUT ctrl15
boxplot(respad)
#@END boxplot

#@BEGIN title
#@IN ctrl11
#@OUT ctrl16
title("Res�duos Padronizados")
#@END title

#@BEGIN qqnorm
#@IN residuos
#@IN ctrl12
#@OUT ctrl17
qqnorm(residuos,ylab="Residuos", main=NULL) 
#@END qqnorm

#@BEGIN qqline
#@IN residuos
#@IN ctrl13
#@OUT ctrl18
qqline(residuos)
#@END qqline

#@BEGIN title
#@IN ctrl14
#@OUT ctrl19
title("Grafico Normal de \n Probabilidade dos Res�duos")
#@END title


#@BEGIN shapiro.test
#@IN residuos
shapiro.test(residuos)
#@END shapiro.test


#@BEGIN aov
#@OUT ex04.avr
ex04.avr <- aov(resp ~ rec/esp)
#@END aov


#@BEGIN aov
#@OUT ex04.ave
ex04.ave <- aov(resp ~ esp/rec)
#@END aov


#@BEGIN TukeyHSD
#@IN ex04.av
#@OUT ex04.tk
#@OUT ctrl20
ex04.tk <- TukeyHSD(ex04.av)
#@END TukeyHSD

#@BEGIN plot
#@IN ex04.tk
#@IN ctrl15
#@OUT ctrl21
plot(ex04.tk)
#@END plot


#@BEGIN resid
#@IN ex04.av
#@OUT s3
s3 <- sum(resid(ex04.av)^2)/ex04.av$df.res
#@END resid

#@BEGIN qtukey
#@IN s3
#@OUT dt
dt <- qtukey(0.95, 3, 18) * sqrt(s3/4)
#@END qtukey

m1 <- ex04.m[,1]

m1d <- outer(m1,m1,"-")

m1d <- m1d[lower.tri(m1d)]

m1n <- outer(names(m1),names(m1),paste, sep="-")
names(m1d) <- m1n[lower.tri(m1n)]

data.frame(dif = m1d, sig = ifelse(abs(m1d) > dt, "*", "ns"))

m2 <- ex04.m[,2]
m2d <- outer(m2,m2,"-")
m2d <- m2d[lower.tri(m2d)]
m2n <- outer(names(m2),names(m2),paste, sep="-")
names(m2d) <- m2n[lower.tri(m2n)]
data.frame(dif = m2d, sig = ifelse(abs(m2d) > dt, "*", "ns"))
#@END Experimento
