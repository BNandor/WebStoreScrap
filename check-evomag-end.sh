if ! grep -q  'rel="next' $1 ;then
	echo 'yes'
else
	echo 'no'
fi
