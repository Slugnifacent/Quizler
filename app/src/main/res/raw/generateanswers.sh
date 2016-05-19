variable="Wolverine,Megaman,Superman,The Ghost, Waterboy,The Mage Runner"
sed -e 's/$/,'"$variable"'/' -i questions.txt