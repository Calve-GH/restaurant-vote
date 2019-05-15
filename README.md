
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
            <td><strong>Get dishes</strong></td>
        </tr>
        <tr>
            <td><strong>URL</strong></td>
            <td><code>/rest/admin/menu/dishes</code></td>
        </tr>
        <tr>
            <td><strong>Method</strong></td>
            <td><strong>GET</strong></td>
        </tr>
        <tr>
            <td><strong>Success Response</strong></td>
            <td><strong>Code:</strong> 200
                <br><strong>Content:</strong><pre>
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
            <td><strong>Error Response</strong></td>
            <td><strong>Code:</strong> 422 UNPROCESSABLE ENTRY
                <br><strong>Content:</strong><pre>{"url": "http://localhost:8080/restaurant_vote/rest/admin/menu", 
                "detail": "Number of menu dishes out of range [2 - 5]"}</pre></td>
        </tr>
        <tr>
            <td><strong>Sample Request</strong></td>
            <td><code>/rest/admin/menu</code></td>
        </tr>
        <tr>
            <td><strong>Notes</strong></td>
            <td><em></em></td>
        </tr>
        <tr>
                 <td><strong>Title</strong></td>
                    <td><strong>Get admin menu</strong></td>
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
                        <br><strong>Content:</strong><pre>
                        {
                            "id": 100008,
                            "date": "2019-05-15",
                            "restaurant": {
                                "id": 100000, "name": "Sweet bobaleh"
                            }
                            ,
                            "items": [ {
                                "id": 100009,
                                "dish": {
                                    "id": 100003, "name": "Soup"
                                }
                                ,
                                "price": 5
                            }
                            ,
                            {
                                "id": 100011,
                                "dish": {
                                    "id": 100005, "name": "Hamburger"
                                }
                                ,
                                "price": 15
                            }
                            ],
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
                        <br><strong>Content:</strong><pre>{"url": "http://localhost:8080/restaurant_vote/rest/admin/menu", 
                        "detail": "Number of menu dishes out of range [2 - 5]"}</pre></td>
                </tr>
                <tr>
                    <td><strong>Sample Request</strong></td>
                    <td><code>/rest/admin/menu</code></td>
                </tr>
                <tr>
                    <td><strong>Notes</strong></td>
                    <td><em></em></td>
                </tr>
    </tbody>
</table>
