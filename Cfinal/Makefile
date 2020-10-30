CC = gcc

FILES = ./utils/fileUtils.c ./words/word.c ./utils/basicUtils.c ./genericArray/genericArray.c

MAIN = cscd240Lab10.c

EXE = ./lab10

all:	${EXE}

${EXE}:	${FILES} ${MAIN}
	${CC} ${FILES} ${MAIN} -o ${EXE}


clean:
	rm -rf ${EXE}
