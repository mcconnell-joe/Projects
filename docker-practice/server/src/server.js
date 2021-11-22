const { loadTypedefsSync } = require("@graphql-tools/load");
const { ApolloServer } = require("apollo-server");
const { GraphQLFileLoader } = require("@graphql-tools/graphql-file-loader");
const { join } = require("path");

const books = [
  {
    title: 'The Awakening',
    author: 'Kate Chopin',
  },
  {
    title: 'City of Glass',
    author: 'Paul Austere',
  },
];

const resolvers = {
  Query: {
    books: () => books,
  },
};

const sources = loadTypedefsSync(join(__dirname, "../schema/*.graphql"), {
  loaders: [new GraphQLFileLoader()],
});

const typeDefs = sources.map(source => source.document);

const server = new ApolloServer({
  typeDefs,
  resolvers
})

server.listen(8080).then(({ url }) => {
  console.log(`🚀  Server ready at ${url}`);
});
