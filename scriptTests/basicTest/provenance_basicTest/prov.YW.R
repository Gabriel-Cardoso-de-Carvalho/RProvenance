#@BEGIN basicTes
# Tests of ddg functions.  No useful computation here.
# This test should not produce any errors and does not use 
# files or snapshots so that ddg text files can be easily 
# compared to be sure everything is working as expected.

# Modified by Luis Perez 7-Jul-2014
# Modified by Luis PErez 17-Jul-2014

## Directories
prov.annotate.on("f")

### Functions
f <- function (a, b, yy, d, e, f) {
  return (a+1)
}
g <- function(x) {
  return(x^2)
}

# Test basic assignments
x <- 1+2
y <- paste("a", "b", "c")
z <- x + 2
w <- x + 3

# Test saving structured data
year <- c(1992, 1995)
name <- c("Ben", "Greg")
male <- c(TRUE, TRUE) 
kids.df <- data.frame(year, name, male)

# Test NA and NULL as values
x <- NA
y <- NULL

# Test function call
#@BEGIN val_<-_f(w,_x,_y,_z,_x_+_1,_vector())
#@IN w
#@IN x
#@IN y
#@IN z
#@OUT val
val <- f(w, x, y, z,  x + 1, vector())
#@END val_<-_f(w,_x,_y,_z,_x_+_1,_vector())
#@BEGIN g
#@OUT x
x <- g(10)
#@END g

# Use a function call on left side of assignment
z <- 5
a <- "character"
storage.mode(z) <- a

# Test .ddg.start and .ddg.finish
.ddg.start("File tests")
# Test files and URLs
#@BEGIN readcsv
#@IN http://harvardforestfasharvardedu/data/p00/hf000/hf000-01-daily-mcsv
#@OUT datadf
data.df <- read.csv ("http://harvardforest.fas.harvard.edu/data/p00/hf000/hf000-01-daily-m.csv")
#@END readcsv
#@BEGIN readcsv
if (FALSE) read.csv ("foo.csv")
#@END readcsv
shortdata.df <- data.df[1:100, ]
#@BEGIN writecsv
#@IN shortdatadf
#@OUT shortdatacsv
write.csv (shortdata.df, "shortdata.csv")
#@END writecsv
#@BEGIN pdf
pdf("airt-vs-prec.pdf")
#@END pdf
#@BEGIN plot
#@IN shortdatadf
plot (shortdata.df$airt, shortdata.df$prec)
#@END plot
#@BEGIN devoff
#@OUT airt-vs-precpdf
dev.off()
#@END devoff
.ddg.finish("File tests")

# Test try-catch
tryCatch (
  foo_val <- foo(),
  error = function(e) {}
)

# Test error
#@BEGIN readcsv
#@IN xcsv
#@OUT data
data <- read.csv( './data/x.csv' , header = FALSE )
#@END readcsv
apply(data,2,sum)
prov.quit()
#@END basicTes
