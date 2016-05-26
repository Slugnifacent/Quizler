cd /d/Development/Quizler/GenerateQuestions
while read line           
do        
	./bin/StringGenerator "$line" temp.txt    
done < source.txt 
./bin/PopulateOptions.sh temp.txt
cp results.txt /d/Development/Quizler/app/src/main/res/raw/questions.txt
rm temp.txt
rm results.txt
sleep 1