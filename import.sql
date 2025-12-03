CONNECT 'jdbc:derby:JavaDB';

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'REGION',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/region.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'NATION',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/nation.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'PART',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/part.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'SUPPLIER',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/supplier.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'PARTSUPP',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/partsupp.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'CUSTOMER',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/customer.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'ORDERS',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/orders.tbl',
    '|',
    null,
    null,
    0
);

CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(
    null,
    'LINEITEM',
    '/home/samuel/NetBeansProjects/TeamProject/raw_tables/lineitem.tbl',
    '|',
    null,
    null,
    0
);