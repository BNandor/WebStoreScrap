cat $1 | grep 'class="card-item js-product-data"..*data-name' | grep  -o 'data-name="..*" data'  | grep  -o '"..*"' | sed 's/"//g'
