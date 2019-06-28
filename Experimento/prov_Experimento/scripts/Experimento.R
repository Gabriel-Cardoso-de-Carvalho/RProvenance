ex04 <- read.table("C:/Users/Gaburieru/Documents/Git/RProvenance/Experimento/dados/exemplo04.txt", header=T)

attach(ex04)

ex04.m <- tapply(resp, list(rec,esp), mean)

ex04.mr <- tapply(resp, rec, mean)

ex04.me <- tapply(resp, esp, mean)

par(mfrow=c(1,2))
interaction.plot(rec, esp, resp)
interaction.plot(esp, rec, resp)

ex04.avi <- aov(resp ~ rec + esp + rec * esp)

ex04.av <- aov(resp ~ rec * esp)

ex04.mt <- model.tables(ex04.av, ty="means")

par(mfrow=c(2,2))
plot(ex04.av)

par(mfrow=c(2,1))
residuos <- resid(ex04.av)

plot(ex04$rec, residuos)
title("Resíduos vs Recipientes")

plot(ex04$esp, residuos)
title("Resíduos vs Espécies")

par(mfrow=c(2,2))
preditos <- (ex04.av$fitted.values)
plot(residuos, preditos)
title("Resíduos vs Preditos")
s2 <- sum(resid(ex04.av)^2)/ex04.av$df.res
respad <- residuos/sqrt(s2)
boxplot(respad)
title("Resíduos Padronizados")
qqnorm(residuos,ylab="Residuos", main=NULL) 
qqline(residuos)
title("Grafico Normal de \n Probabilidade dos Resíduos")

shapiro.test(residuos)

ex04.avr <- aov(resp ~ rec/esp)

ex04.ave <- aov(resp ~ esp/rec)

ex04.tk <- TukeyHSD(ex04.av)
plot(ex04.tk)

s3 <- sum(resid(ex04.av)^2)/ex04.av$df.res
dt <- qtukey(0.95, 3, 18) * sqrt(s3/4)

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
