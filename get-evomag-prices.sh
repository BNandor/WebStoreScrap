cat $1 | grep 'real_price' | sed 's/..*real_price">\([.,0-9][.,0-9]*\)/\1/g' | sed 's/[^.,0-9]//g' |sed 's/,..*//g' | sed 's/\.//g'
