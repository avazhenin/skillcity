Rest api test

----------------------------- Fetch-POST -----------------------------

fetch(
'/api/getlist',
 {
  method: 'POST',
  headers:{'Content-Type':'application/json'},
  body:JSON.stringify({text:'Forth message'})
 }
).then(result => console.log(result))

----------------------------- Fetch-PUT -----------------------------

fetch(
'/api/getlist/4',
 {
  method: 'PUT',
  headers:{'Content-Type':'application/json'},
  body:JSON.stringify({id:'4', text:'some 4th msg'})
 }
).then(result => console.log(result))

----------------------------- Fetch-DELETE -----------------------------
fetch('/api/getlist/4',{method: 'DELETE'}).then(result => console.log(result))