package com.thamizhi.task1.api

import com.apollographql.apollo.ApolloClient

var apolloClient=ApolloClient.builder().serverUrl("http://134.209.47.247:4000/graphql").build()
