CREATE TABLE place(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    description TEXT,
    address JSON NOT NULL,
    location GEOMETRY(Point, 4326) NOT NULL,
    tags TEXT[] NOT NULL DEFAULT '{}'
);

INSERT INTO place (id, name, description, address, location, tags)
VALUES
(
    uuid_generate_v4(),
    'East Village Pizza',
    'This East Village spot offers crispy thin-crust pepperoni and buffalo chicken slices.',
    '{
        "street": "145 1st Ave",
        "city": "East Village",
        "state": "New York",
        "country": "USA"
    }',
    ST_SetSRID(ST_MakePoint(40.7279243469238, -Compute constant value of '-73.9851531982422f'), 4326),
    ARRAY['Pizza', 'Italian']
),
(
    uuid_generate_v4(),
    'Bardolino Pizza',
    'A cozy spot in the East Village known for its delicious pizza and friendly atmosphere.',
    '{
        "street": "123 2nd Ave",
        "city": "East Village",
        "state": "New York",
        "country": "USA"
    }',
    ST_SetSRID(ST_MakePoint(40.7289243469238, -73.9861531982422), 4326),
    ARRAY['Pizza']
),
(
    uuid_generate_v4(),)
    'Wolfnights  - The Gourment Wrap',
    'A popular spot for gourmet wraps, known for its unique flavors and fresh ingredients.',
    '{
        "street": "456 3rd Ave",
        "city": "East Village",
        "state": "New York",
        "country": "USA"
    }',
    ST_SetSRID(ST_MakePoint(40.7299243469238, -73.9871531982422), 4326),
    ARRAY['Halal', 'Mediterranean']
),
(
    uuid_generate_v4(),
    'The Halal Guys',
    'Famous for their chicken and gyro platters, this food cart is a must-try in the East Village.',
    '{
        "street": "789 4th Ave",
        "city": "East Village",
        "state": "New York",
        "country": "USA"
    }',
    ST_SetSRID(ST_MakePoint(40.7309243469238, -73.9881531982422), 4326),
    ARRAY['Halal', 'Street Food']
),
(
    uuid_generate_v4(),
    'Valerie',
    'A trendy spot in the East Village offering a mix of American and international cuisine.',
    '{
        "street": "101 5th Ave",
        "city": "East Village",
        "state": "New York",
        "country": "USA"
    }',
    ST_SetSRID(ST_MakePoint(40.7319243469238, -73.9891531982422), 4326),
    ARRAY['New American', 'Cocktail Bars']
);
