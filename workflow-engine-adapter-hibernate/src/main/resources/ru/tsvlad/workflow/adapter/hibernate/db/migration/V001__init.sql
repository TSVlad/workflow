CREATE TABLE processes
(
    id             UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    idempotence_id TEXT             NOT NULL,
    process_name   TEXT             NOT NULL,
    status         TEXT             NOT NULL,
    created        TIMESTAMPTZ               DEFAULT current_timestamp,
    params         TEXT,
    paramClass     TEXT
);