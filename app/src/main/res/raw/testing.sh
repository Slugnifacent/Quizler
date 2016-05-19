# Gather Answers
array=( $(awk -F "|" '{print $2}' test.txt))

> questions.txt 
while read line           
do        
	arrayCount=${#array[@]}   
	A=$(($RANDOM % $arrayCount))
	B=$(($RANDOM % $arrayCount))
	C=$(($RANDOM % $arrayCount))
	D=$(($RANDOM % $arrayCount))
	E=$(($RANDOM % $arrayCount))
	F=$(($RANDOM % $arrayCount))

	OptionA=${array[A]}
	OptionB=${array[B]}
	OptionC=${array[C]}
	OptionD=${array[D]}
	OptionE=${array[E]}
	OptionF=${array[F]}

    variable="OPTIONS"
	replace="$OptionA|$OptionB|$OptionC|$OptionD|$OptionE|$OptionF"
    

    echo "$line" | sed -e 's/'"$variable"'/'"$replace"'/'  >> questions.txt        
done < test.txt   

