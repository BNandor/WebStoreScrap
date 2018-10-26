awk ' $0 ~ /npi_image/{getline;	print $0;}
' $1 | sed 's/..*title="\(..*\)" href..*/\1/g'
