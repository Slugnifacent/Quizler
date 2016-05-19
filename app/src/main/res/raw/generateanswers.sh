variable=",Wolverine,Megaman,Superman,The Ghost, Waterboy,The Mage Runner"
replace="|Wolverine|Megaman|Superman|The Ghost| Waterboy|The Mage Runner"
sed -e 's/'"$variable"'/'"$replace"'/' -i questions.txt