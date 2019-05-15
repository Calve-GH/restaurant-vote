<h3>API Entry</h3>
<table cellspacing="2" border="1" cellpadding="5">
    <thead>
        <tr>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <td><strong>Title</strong></td>
            <td><strong>Get restaurant admin menu</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/admin/menu</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>GET, POST</strong></td>
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
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/admin/menu</code></td>
        </tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Get dishes</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/dishes</code></td>
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
            <td><code>/rest/admin/menu/dishes</code></td>
        </tr>
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
            	[
            		{
            	    "id": 100021,
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
                        "voteCount": 11
                    }
            	]
            </pre></td>
        </tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile</code></td>
        </tr>
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
            <td><strong>PUT, DELETE</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 204
        </tr>
        <tr>
            <td><strong>Error Response</strong></td>
            <td><strong>Code:</strong> 401 UNAUTHORIZED
        </tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/vote/100000</code></td>
        </tr>
        <tr>
            <td><strong>Notes</strong></td>
            <td><em></em></td>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Vote History</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/vote/history</code></td>
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
            <td><code>/rest/profile/vote/history</code></td>
        </tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Vote Restaurant History</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/vote/history/id</code></td>
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
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/vote/history/100000</code></td>
        </tr>
        <tr>
            <td><strong>Title</strong></td>
            <td><strong>Restaurants</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/profile/vote/restaurants</code></td>
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
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/profile/vote/restaurants</code></td>
        </tr>
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
<p>Get all restaurants:
curl http://localhost:8080/restaurant_vote/rest/profile/restaurants -u ivanov@gmail.com:1234567</p>
<p>Save, update admin menu:
curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '[{"id":100003,"name":"Soup","price":15.0},{"id":100004,"name":"French fries","price":10.0},{"id":100007,"name":"Coffee","price":5.0}]'  http://localhost:8080/restaurant_vote/rest/admin/menu -u ivanov@gmail.com:1234567</p>
<p>Get all dishes:
curl http://localhost:8080/restaurant_vote/rest/admin/menu/dishes -u ivanov@gmail.com:1234567</p>
<p>Vote for restaurant with id '100000':
curl -X PUT http://localhost:8080/restaurant_vote/rest/profile/vote/100000 -u ivanov@gmail.com:1234567</p>
<p>Unlock vote before 11.00:
curl -X DELETE http://localhost:8080/restaurant_vote/rest/profile/vote -u ivanov@gmail.com:1234567</p>
<p>Get vote list:
curl http://localhost:8080/restaurant_vote/rest/profile -u ivanov@gmail.com:1234567</p>
<p>Get all history:
curl http://localhost:8080/restaurant_vote/rest/profile/vote/history -u ivanov@gmail.com:1234567</p>
<p>Get history for restaurant with id '100000':
curl http://localhost:8080/restaurant_vote/rest/profile/vote/history/100000 -u ivanov@gmail.com:1234567</p>
<p>Register new restaurants admin:
curl -H "Content-Type: application/json;charset=UTF-8" -X POST -d '{"name":"newName","email":"newemail@ya.ru","password":"newPassword","newRestaurantName":"Mandondo"}'  http://localhost:8080/restaurant_vote/rest/profile/register</p>

<p>Обоснование кэша:</p>

<p>Dish, Restaurant - NONSTRICT_READ_WRITE, справочники, почти не меняются, время жизни 4 часа, Evict только в случае добавления.</p>
<p>History - READ_ONLY, не меняется, время жизни 24ч, Clear только при системном рестарте.</p>
<p>Menu - NONSTRICT_READ_WRITE, время жизни минута, Evict при Save, очень большое колличество запросов от пользователей, поэтому постоянно лезть в базу
за синхронизированными данными не вариант.</p>

<p>Хотелка(Сильно не бейте, 2 работы, семья, времени в обрез, буду рад конструктивной критике 8) ):
<p>1) Есть недоработанные моменты по рекомендациям. Оставил POSTRESQL, думал перейду на HSQL без проблем, 
бился целый день, но триггер у меня так и не стал, если будет время в ближайшее время, сделаю.</p>
<p>2) Не предусмотрел возможность Shutdown server'a, при переходе таймера в 11ч и 00.00.</p>
<p>3) Не нравится история голосований, упростил как вариант связи Меню-Ресторан-Еда, необходим Pagination.</p>
<p>4) Не уверен по правильности REST.</p>
