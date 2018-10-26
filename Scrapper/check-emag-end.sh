if cat $1 | grep -q '<a href="javascript:void(0)">Pagina urmatoare</a>' ;then
	echo 'yes'
else
	echo 'no'
fi
