<h3>API Entry</h3>
<table cellspacing="2" border="1" cellpadding="5">
    <thead>
<tr><th></th><th></th></tr>
    </thead>
    <tbody>
<tr>
<td><strong>Title</strong></td>
    <td><strong>Get restaurant menu</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/menu</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>GET</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 200
<strong>Content:</strong><pre>
{
    "id": 100008,
    "date": "2019-05-15",
    "restaurant": 
{"id": 100000, "name": "Sweet bobaleh"},
    "items": 
[{"id": 100009,"dish":{"id": 100003, "name": "Soup"},"price": 5},
{"id": 100011,"dish": {"id": 100005, "name": "Hamburger"},"price": 15}],
    "voteCount": 11
}
</pre></td>
</tr>
<tr>
    <td><strong>URL Parameters</strong></td>
    <td>date=[LocalDate], restaurantId=[Integer]
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/menu?date=2019-05-26&restaurantId=100000</code></td>
</tr>
<tr><th></th><th></th></tr>
<tr>
<td><strong>Title</strong></td>
    <td><strong>Save restaurant menu</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/menu</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>POST</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 201
</td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 409 CONFLICT
    <pre>
    {"url": "http://localhost:8080/restaurant_vote/rest/admin/menu",
"detail": "Menu with this restaurant and date already exists"}
    </pre>
    </td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
<strong>Content:</strong><pre>{"url": "http://localhost:8080/restaurant_vote/rest/admin/menu", 
"detail": "Number of menu dishes out of range [2 - 5]"}</pre></td>
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/menu</code></td>
</tr>
<tr><th></th><th></th></tr>
<tr>
<td><strong>Title</strong></td>
    <td><strong>Update restaurant menu</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/menu</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>PUT</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 204
</td>
</tr>
<tr>
    <td><strong>URL Parameters</strong></td>
    <td> id=[Integer]
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
<strong>Content:</strong><pre>{"url": "http://localhost:8080/restaurant_vote/rest/admin/menu", 
"detail": "Number of menu dishes out of range [2 - 5]"}</pre></td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 409 CONFLICT
    <pre>
    {"url": "http://localhost:8080/restaurant_vote/rest/admin/menu",
"detail": "Menu with this restaurant and date already exists"}
    </pre>
    </td>
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/menu?id=100008</code></td>
</tr>
<tr><th></th><th></th></tr>
<tr>
<td><strong>Title</strong></td>
    <td><strong>Delte restaurant menu</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/menu/id</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>DELETE</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 204
</td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY</td>
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/menu/100008</code></td>
</tr>
<tr><th></th><th></th></tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Get dishes</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>rest/admin/dish</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>GET</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 200
        <strong>Content:</strong><pre>
        [{"id": 100003, "name": "Soup"},
        {"id": 100004, "name": "French fries"},
        {"id": 100005, "name": "Hamburger"},
        {"id": 100006, "name": "Tea"},
        {"id": 100007, "name": "Coffee"}]
        </pre></td>
        </tr>
        <tr>
            <td><strong>Error Response</strong></td>
            <td><strong>Code:</strong> 401 UNAUTHORIZED
        </tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/admin/dish</code></td>
        </tr>
		<tr><th></th><th></th></tr>
        <tr>
 <td><strong>Title</strong></td>
 <td><strong>Add new dish</strong></td>
                </tr>
                <tr>
 <td><strong>URL</strong></td>
 <td><code>/rest/admin/dish</code></td>
                </tr>
                <tr>
 <td><strong>Method</strong></td>
 <td><strong>POST</strong></td>
                </tr>
                <tr>
 <td><strong>Success Response</strong></td>
 <td><strong>Code:</strong> 201
                <strong></td>
                </tr>
                <tr>
 <td><strong>Error Response</strong></td>
 <td><strong>Code:</strong> 401 UNAUTHORIZED
                </tr>
 <tr>
     <td><strong>Error Response</strong></td>
     <td><strong>Code:</strong> 409 CONFLICT 
     <pre>
     {"url": "http://localhost:8080/restaurant_vote/rest/admin/dish",
 "detail": "Dish with this name already exists"}
     </pre>
     </td>
 </tr>
                <tr>
 <td><strong>Sample Request</strong></td>
 <td><code>/rest/admin/dish</code></td>
                </tr>
				<tr><th></th><th></th></tr>
        <tr>
    <td><strong>Title</strong></td>
    <td><strong>Delete dish</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/dish/id</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>DELETE</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 204
<strong></td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
    <pre>
    {"url": "http://localhost:8080/restaurant_vote/rest/admin/dish/1000031",
        "detail": "Not found entity with id=1000031"}
    </pre>
    </td>
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/dish/100005</code></td>
</tr>
<tr><th></th><th></th></tr>
<tr>
    <td><strong>Title</strong></td>
    <td><strong>Get restaurants</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/profile/restaurant</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>GET</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 200
    <strong>Content:</strong><pre>
    [{
   "id": 100000,
   "name": "Sweet bobaleh"},
    {
   "id": 100001,
   "name": "ITAKA"},
    {
   "id": 100002,
   "name": "Hunter Village"
    }]
    </pre></td>
</tr>
				   <tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 204
<strong></td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/profile/restaurants</code></td>
</tr>     <tr><th></th><th></th></tr>
    <tr>
    <td><strong>Title</strong></td>
    <td><strong>Add new restaurant</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/restaurant</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>POST</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 201
<strong></td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
							    <tr>
     <td><strong>Error Response</strong></td>
     <td><strong>Code:</strong> 409 CONFLICT 
     <pre>
     {"url": "http://localhost:8080/restaurant_vote/rest/admin/restaurant",
 "detail": "Restaurant with this name already exists"}
     </pre>
     </td>
 </tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/restaurant</code></td>
</tr><tr><th></th><th></th></tr>
    <tr>
    <td><strong>Title</strong></td>
    <td><strong>Delete restaurant</strong></td>
</tr>
<tr>
    <td><strong>URL</strong></td>
    <td><code>/rest/admin/restaurant/id</code></td>
</tr>
<tr>
    <td><strong>Method</strong></td>
    <td><strong>DELETE</strong></td>
</tr>
<tr>
    <td><strong>Success Response</strong></td>
    <td><strong>Code:</strong> 204
<strong></td>
</tr>
<tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 401 UNAUTHORIZED
</tr>
									   <tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
    <pre>
    {"url": "http://localhost:8080/restaurant_vote/rest/admin/restaurant/1000031",
        "detail": "Not found entity with id=1000031"}
    </pre>
    </td>
</tr>
<tr>
    <td><strong>Sample Request</strong></td>
    <td><code>/rest/admin/restaurant/100000</code></td>
</tr> <tr><th></th><th></th></tr>
<tr>
            <td><strong>Title</strong></td>
            <td><strong>Vote list</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>GET</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 200
            <strong>Content:</strong><pre>
            	[{"id": 100021,
            			"date": "2019-05-15",
            			"restaurant": {
            		    "id": 100001,"name": "ITAKA"},
            		    "items": [ {
            "id": 100009,
            "dish": {
            "id": 100003, "name": "Soup"},
            "price": 5},{
            "id": 100011,
            "dish": {
            "id": 100005, "name": "Hamburger"},
            "price": 15}],
            		    "voteCount": 1
            		},
            		{
             "id": 100008,
            "date": "2019-05-15",
            "restaurant": {
            "id": 100000, "name": "Sweet bobaleh"},
            "items": [ {
            "id": 100009,
            "dish": {
            "id": 100003, "name": "Soup"},
            "price": 5},{
            "id": 100011,
            "dish": {
            "id": 100005, "name": "Hamburger"},
            "price": 15}],
            "voteCount": 11}]
            </pre></td>
        </tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/vote</code></td>
        </tr><tr><th></th><th></th></tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Vote</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/vote</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>POST</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 204
        </tr>
		        <tr>
            <td><strong>URL Parameters</strong></td>
            <td><strong>Required:</strong> id=[integer]
        </tr>
        <tr>
            <td><strong>Error Response</strong></td>
            <td><strong>Code:</strong> 401 UNAUTHORIZED
        </tr>
		<tr>
            <td><strong>Error Response</strong></td>
            <td><strong>Code:</strong> 409 CONFLICT
			<pre>
			{
    "url": "http://localhost:8080/restaurant_vote/rest/profile/vote",
    "detail": "Vote with this user and date already exists"}
			</pre>
			</td>
        </tr>
											   <tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
    <pre>
    {"url": "http://localhost:8080/restaurant_vote/rest/profile/vote/1000031",
        "detail": "Not found entity with id=1000031"}
    </pre>
    </td>
</tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/vote?id=100000</code></td>
        </tr><tr><th></th><th></th></tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Get vote history</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/history</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>GET</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 200
            <strong>Content:</strong><pre>
            [{
                "id": 100022,
                "date": "2019-05-14",
                "restaurant": {
                "id": 100000,
                "name": "Sweet bobaleh"},
                "data": "Soup:10.5 French fries:10.0 Coffee:5.3",
                "count": 112
            },
            {
                "id": 100024,
                "date": "2019-05-14",
                "restaurant": {
                "id": 100001,
                "name": "ITAKA"},
                "data": "Soup:7.0 Hamburger:11.0 Tea:3.4 Coffee:4.3",
                "count": 179
            },
            {
                "id": 100023,
                "date": "2019-05-13",
                "restaurant": {
                "id": 100000,
                "name": "Sweet bobaleh"},
                "data": "Hamburger:13.12 Tea:3.0",
                "count": 79
            }
            ]
            </pre></td>
        </tr>
		<tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/history</code></td>
        </tr><tr><th></th><th></th></tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Vote Restaurant History</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/history/id</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>GET</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 200
            <strong>Content:</strong><pre>
            [{
                "id": 100022,
                "date": "2019-05-14",
                "restaurant": {
                "id": 100000,
                "name": "Sweet bobaleh"},
 "data": "Soup:10.5 French fries:10.0 Coffee:5.3",
 "count": 112}
            ,{
                "id": 100023,
                "date": "2019-05-13",
                "restaurant": {
                "id": 100000,
                "name": "Sweet bobaleh"},
 "data": "Hamburger:13.12 Tea:3.0",
 "count": 79
            }]
            </pre></td>
        </tr>
		<tr>
            <td><strong>Error Response</strong></td>
            <td><strong>Code:</strong> 401 UNAUTHORIZED
        </tr>
		   <tr>
    <td><strong>Error Response</strong></td>
    <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
    <pre>
    {"url": "http://localhost:8080/restaurant_vote/rest/profile/vote/1000031",
        "detail": "Not found entity with id=1000031"}
    </pre>
    </td>
	</tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/history/100000</code></td>
        </tr><tr><th></th><th></th></tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Register user</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/register</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>POST</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 201
        </tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/register</code></td>
        </tr>
    </tbody>
</table>

CURL:
<p>Save admin menu:
curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '{"date":"2019-05-27","restaurant":{"id":100002,"name":"Hunter Village","menu_exist":false},"menuItems":[{"dish":{"id":100005,"name":"Hamburger"},"price":15.1},{"dish":{"id":100006,"name":"Tea"},"price":2.1},{"dish":{"id":100007,"name":"Coffee"},"price":3.2}],"voteCount":0}'  http://localhost:8080/restaurant_vote/rest/admin/menu -u ivanov@gmail.com:1234567</p>
<p>Get all menus
curl http://localhost:8080/restaurant_vote/rest/admin/menu -u ivanov@gmail.com:1234567</p>
<p>Get menu by restaurant id
curl http://localhost:8080/restaurant_vote/rest/admin/menu?restaurantId=100000 -u ivanov@gmail.com:1234567</p>
<p> Get menu by restaurant id and date
curl http://localhost:8080/restaurant_vote/rest/admin/menu?date=2019-05-27&restaurantId=100000 -u ivanov@gmail.com:1234567</p>
<p>Get all dishes
curl http://localhost:8080/restaurant_vote/rest/admin/dish -u ivanov@gmail.com:1234567</p>
<p>Get all restaurants
curl http://localhost:8080/restaurant_vote/rest/admin/restaurant -u ivanov@gmail.com:1234567</p>
<p>Get vote list:
curl http://localhost:8080/restaurant_vote/rest/profile -u ivanov@gmail.com:1234567</p>
<p>Get all history:
curl http://localhost:8080/restaurant_vote/rest/profile/history -u ivanov@gmail.com:1234567</p>
<p>Get history for restaurant with id '100000':
curl http://localhost:8080/restaurant_vote/rest/profile/history/100000 -u ivanov@gmail.com:1234567</p>
<p>Vote for restaurant with id '100000':
curl -X POST http://localhost:8080/restaurant_vote/rest/profile/vote?id=100000 -u ivanov@gmail.com:1234567</p>
<p>Обоснование кэша:</p>

<p>Dish, Restaurant - NONSTRICT_READ_WRITE, справочники, почти не меняются, время жизни 4 часа.</p>
<p>History - READ_ONLY, не меняется, время жизни 24ч, Clear только при системном рестарте.</p>
<p>Menu - NONSTRICT_READ_WRITE, время жизни минута, очень большое колличество запросов от пользователей, поэтому постоянно лезть в базу
за синхронизированными данными не вариант.</p>